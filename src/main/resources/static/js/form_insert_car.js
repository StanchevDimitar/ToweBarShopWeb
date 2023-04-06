const makeDropdown = document.getElementById("makeDropdown");

fetch("/api/makes")
    .then(response => response.json())
    .then(makes => {
        makes.forEach(make => {
            const option = document.createElement("option");

            option.value = make.id;
            option.text = make.name;
            makeDropdown.add(option);
        });
    });
const modelDropdown = document.getElementById("modelDropdown");
const defaultOptionModel = document.createElement("option");
defaultOptionModel.value = "-1";
defaultOptionModel.text = "-Select model-";
modelDropdown.add(defaultOptionModel, null);
makeDropdown.addEventListener("change", () => {
    const selectedMakeId = makeDropdown.value;
    fetch(`/api/models?makeId=${selectedMakeId}`)
        .then(response => response.json())
        .then(models => {
            // Clear existing options
            modelDropdown.innerHTML = "";
            modelDropdown.add(defaultOptionModel, null);
            // Add new options
            models.forEach(model => {
                const option = document.createElement("option");
                option.value = model.id;
                option.text = model.name;
                modelDropdown.add(option);
            });
        });
});
const generationDropdown = document.getElementById("generationDropdown");
const defaultOptionGeneration = document.createElement("option");
defaultOptionGeneration.value = "-1";
defaultOptionGeneration.text = "-Select generation-";
generationDropdown.add(defaultOptionGeneration, null);

modelDropdown.addEventListener("change", () => {
    const selectedModelId = modelDropdown.value;
    fetch(`/api/generations?modelId=${selectedModelId}`)
        .then(response => response.json())
        .then(generations => {
            // Clear existing options
            generationDropdown.innerHTML = "";
            generationDropdown.add(defaultOptionGeneration, null);
            // Add new options
            generations.forEach(generation => {
                const option = document.createElement("option");
                option.value = generation.id;
                option.text = generation.name;
                generationDropdown.add(option);
            });
        });
});
const seriesDropdown = document.getElementById("seriesDropdown");
fetch("/api/series")
    .then(response => response.json())
    .then(series => {
        series.forEach(serie => {
            const option = document.createElement("option");
            option.value = serie.id;
            option.text = serie.name;
            seriesDropdown.add(option);
        });
    });
const trimDropdown = document.getElementById("trimDropdown");
const defaultOptionTrim = document.createElement("option");
defaultOptionTrim.value = "-1";
defaultOptionTrim.text = "-Select trim-";
trimDropdown.add(defaultOptionTrim, null);
seriesDropdown.addEventListener("change", () => {
    const selectedSeriesId = seriesDropdown.value;
    const selectedModelId = modelDropdown.value;
    fetch(`/api/trim?modelId=${selectedModelId}&seriesId=${selectedSeriesId}`)
        .then(response => response.json())
        .then(trims => {
            // Clear existing options
            trimDropdown.innerHTML = "";
            trimDropdown.add(defaultOptionTrim, null);
            // Add new options
            trims.forEach(trim => {
                const option = document.createElement("option");
                option.value = trim.id;
                option.text = trim.name;
                trimDropdown.add(option);
            });
        });
});
