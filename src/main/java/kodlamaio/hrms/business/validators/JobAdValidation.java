package kodlamaio.hrms.business.validators;

import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAd;

public class JobAdValidation {
    public static Result checkValidate(JobAd jobAd) {
        boolean result = true;

        if (!ValidationBase.requiredInteger(jobAd.getJob().getId()))
            result = false;

        if (!ValidationBase.requiredInteger(jobAd.getCity().getId()))
            result = false;

        if (!ValidationBase.requiredInteger(jobAd.getEmployer().getId()))
            result = false;

        if (!ValidationBase.requiredString(jobAd.getDescription()))
            result = false;

        if (!ValidationBase.requiredInteger(jobAd.getOpenPositionCount()))
            result = false;

        if (!ValidationBase.requiredLocalDate(jobAd.getApplicationDeadline()))
            result = false;

        if (!result)
            return new ErrorResult("Boş alan bırakmayın");

        return null;
    }
}
