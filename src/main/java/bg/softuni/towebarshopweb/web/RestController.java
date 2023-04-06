package bg.softuni.towebarshopweb.web;

import bg.softuni.towebarshopweb.model.entity.CarEntities.*;
import bg.softuni.towebarshopweb.repository.CarRepositories.*;
import bg.softuni.towebarshopweb.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {


    private final UserService userService;
    private final MakeRepository makeRepository;
    private final ModelRepository modelRepository;
    private final GenerationRepository generationRepository;
    private final SerieRepository serieRepository;
    private final TrimRepository trimRepository;

    public RestController(UserService userService, MakeRepository makeRepository, ModelRepository modelRepository, GenerationRepository generationRepository, SerieRepository serieRepository, TrimRepository trimRepository) {
        this.userService = userService;
        this.makeRepository = makeRepository;
        this.modelRepository = modelRepository;
        this.generationRepository = generationRepository;
        this.serieRepository = serieRepository;
        this.trimRepository = trimRepository;
    }

    @GetMapping("/makes")
    public List<Make> getAllMakes() {
        return makeRepository.findAll();
    }

    @GetMapping("/models")
    public List<Model> getModelsByMakeId(@RequestParam Long makeId) {
        List<Model> byMakeId = modelRepository.findByMakeId(makeId);
        return byMakeId;
    }

    @GetMapping("/generations")
    public List<Generation> getGenerationByModelId(@RequestParam Long modelId) {
        List<Generation> byMakeId = generationRepository.findByModelId(modelId);
        return byMakeId;
    }

    @GetMapping("/series")
    public List<Serie> getAllSeries() {
        return serieRepository.findAll();
    }

    @GetMapping("/trim")
    public List<Trim> getTrimsByModelAndSeries(
            @RequestParam Long modelId,
            @RequestParam Long seriesId) {
        List<Trim> byModelIdAndSerieId = trimRepository.findByModelIdAndSerieId(modelId, seriesId);
        return byModelIdAndSerieId;
    }

}
