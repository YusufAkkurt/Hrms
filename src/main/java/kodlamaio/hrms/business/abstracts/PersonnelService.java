package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.core.utilities.results.Result;

public interface PersonnelService {
    Result validateEmployer(int userId);
}
