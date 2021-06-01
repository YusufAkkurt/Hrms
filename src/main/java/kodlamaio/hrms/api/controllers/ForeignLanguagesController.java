package kodlamaio.hrms.api.controllers;

import kodlamaio.hrms.business.abstracts.ForeignLanguageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.ForeignLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/foreignLanguages")
public class ForeignLanguagesController {
    private final ForeignLanguageService foreignLanguageService;

    @Autowired
    public ForeignLanguagesController(ForeignLanguageService foreignLanguageService) {
        this.foreignLanguageService = foreignLanguageService;
    }

    @GetMapping
    public DataResult<List<ForeignLanguage>> getAll() {
        return this.foreignLanguageService.getAll();
    }

    @PostMapping
    public Result add(@RequestBody ForeignLanguage foreignLanguage) {
        return this.foreignLanguageService.add(foreignLanguage);
    }
}

