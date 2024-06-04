package power.debitservice.exception

/**
 * Исключение, возникающее при отсутствии картинки в хранилище
 * @author П.Галомзик
 */
class ImageNotFoundException(message:String?=null):RuntimeException(message) {
}