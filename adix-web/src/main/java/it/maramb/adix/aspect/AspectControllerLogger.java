package it.maramb.adix.aspect;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableAspectJAutoProxy
@Slf4j
public class AspectControllerLogger {

    @PostConstruct
    private void postConstruct() {
        log.info("[ADIX ASPECT LOGGER] Inizializzazione in corso proxy controller");
    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void aspectController() {
    }

    @Around("aspectController()")
    public ResponseEntity aspectControllerLogger(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        Signature signature = joinPoint.getSignature();
        logger.info("[{}] > Nuova richiesta", signature.getName());

        try {
            ResponseEntity response = (ResponseEntity) joinPoint.proceed();

            if (response.getStatusCode().is2xxSuccessful()) {
                logger.info("[{}] Risposta inviata", signature.getName());
            } else {
                logger.warn("[{}] Risposta di errore inviata", signature.getName());
            }

            return response;
        } catch (Throwable e) {
            logger.info("[{}] Eccezione non gestita", signature.getName());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
