package power.debitservice.aspect

    import io.github.oshai.kotlinlogging.KotlinLogging
    import org.aspectj.lang.JoinPoint
    import org.aspectj.lang.annotation.After
    import org.aspectj.lang.annotation.AfterThrowing
    import org.aspectj.lang.annotation.Aspect
    import org.aspectj.lang.annotation.Pointcut
    import org.springframework.stereotype.Component
    import java.lang.Exception

    @Aspect
    @Component
    class LoggingAspect {
        private val logger = KotlinLogging.logger {}

        @Pointcut("execution(* power.debit.service.*.*(..))")
        fun cardServiceMethods() {

        }

        @Pointcut("within(power.debitservice.*)")
        fun controllerAdviceMethod() {

        }

        @After("cardServiceMethods()")
        fun logCardServiceMethod(joinPoint: JoinPoint) {
            val methodName = joinPoint.signature.name
            val className = joinPoint.target.javaClass.simpleName
            logger.info { " Method was called: $methodName class $className" }

        }

        @AfterThrowing(pointcut = "cardServiceMethods()", throwing = "exception")
        fun logAfterException(joinPoint: JoinPoint, exception: Exception) {
            val methodName = joinPoint.signature.name
            val className = joinPoint.target.javaClass.simpleName
            logger.warn { " Method $methodName class $className throw exception ${exception.message}" }
        }

    }
