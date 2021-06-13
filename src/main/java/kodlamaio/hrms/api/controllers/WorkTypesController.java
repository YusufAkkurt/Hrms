package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.WorkTypeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.WorkType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/workTypes")
public class WorkTypesController {
    private final WorkTypeService workTypeService;

    @Autowired
    public WorkTypesController(WorkTypeService workTypeService) {
        this.workTypeService = workTypeService;
    }

    @GetMapping("getAll")
    public DataResult<List<WorkType>> getAll(){
        return this.workTypeService.getAll();
    }
}
