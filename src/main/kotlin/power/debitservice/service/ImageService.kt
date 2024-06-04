package power.debitservice.service

import io.minio.GetObjectArgs
import io.minio.MinioClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


/**
 * Сервис для получения изображения банковской карты из файлового хранилища minio

 * @exception RuntimeException если картинка не найдена в хранилище minio
 */

@Service
class ImageService {
    @Autowired
    lateinit var storageProperties: StorageProperties

    @Autowired
    lateinit var minioClient: MinioClient

    /**
     * Метод получения изображения из файлового хранилища по ID карточного продукта

     */
    fun getImageById(prodId: Int): ByteArray {
        val bucketName = storageProperties.bucket
        val imageName = prodId.toString()

        try {
            return minioClient.getObject(
                GetObjectArgs.builder()
                    .bucket(bucketName)
                    .`object`(imageName).build()
            ).use {
                it.readBytes()
            }
        } catch (exception: Exception) {
            throw RuntimeException("Can't get image by given id", exception)
        }
    }

}