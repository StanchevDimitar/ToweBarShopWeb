const makeDropdown = document.getElementById("makeDropdown");
const yearDropdown = document.getElementById("yearDropdown");
const modelDropdown = document.getElementById("modelDropdown");
const generationDropdown = document.getElementById("generationDropdown");
const bodyDropdown = document.getElementById("bodyDropdown");

const options = {
    method: 'GET',
    headers: {
        'X-RapidAPI-Key': 'ebccc93d59msh8ebb6fd1bbad40ep170ab6jsn2cf9e3e92644',
        'X-RapidAPI-Host': 'car-api2.p.rapidapi.com'
    }
};
makeDropdown.addEventListener("click", () => {
    fetch('https://car-api2.p.rapidapi.com/api/makes?direction=asc&sort=name', options)
        .then(response => response.json())
        .then(make => {
            let data = make.data;

            data.forEach(make => {
                const option = document.createElement("option");
                option.value = make.id;
                option.text = make.name;
                makeDropdown.add(option);
            })
        })
})

const defaultOptionYear = document.createElement("option");
defaultOptionYear.value = "-1";
defaultOptionYear.text = "-Select generation-";
yearDropdown.add(defaultOptionYear, null);

makeDropdown.addEventListener("change", () => {
    // Clear existing options
    yearDropdown.innerHTML = "";
    yearDropdown.add(defaultOptionYear, null);

    // Populate the years
    for (let year = 2015; year <= 2023; year++) {
        const option = document.createElement("option");
        option.value = String(year);
        option.text = String(year);
        yearDropdown.add(option);
    }
});


const defaultOptionModel = document.createElement("option");
defaultOptionModel.value = "-1";
defaultOptionModel.text = "-Select model-";
modelDropdown.add(defaultOptionModel, null);
yearDropdown.addEventListener("change", () => {
    const selectedMakeId = makeDropdown.value;
    fetch(`https://car-api2.p.rapidapi.com/api/models?sort=name&direction=asc&year=${yearDropdown.value}&verbose=yes&make_id=${selectedMakeId}`, options)
        .then(response => response.json())
        .then(data => {
            const models = data.data; // Access the array of model objects from the 'data' property

            // Clear existing options
            modelDropdown.innerHTML = "";
            modelDropdown.add(defaultOptionModel, null);

            // Add new options
            models.forEach(model => {
                const option = document.createElement("option");
                option.value = model.id;
                option.text = model.name; // Displaying both make and model names
                modelDropdown.add(option);
            });
        });
});

const defaultOptionGeneration = document.createElement("option");
defaultOptionGeneration.value = "-1";
defaultOptionGeneration.text = "-Select generation-";
generationDropdown.add(defaultOptionGeneration, null);

modelDropdown.addEventListener("change", async () => {
    const modelName = modelDropdown.options[modelDropdown.selectedIndex].text.replaceAll(" ", "+");
    const currentUrl = `https://car-api2.p.rapidapi.com/api/trims?direction=asc&sort=name&year=${yearDropdown.value}&model=${modelName}&verbose=yes&make_id=${makeDropdown.value}`;

    fetch(currentUrl, options)
        .then(response => response.json())
        .then(generations => {
            // Clear existing options
            generationDropdown.innerHTML = "";
            generationDropdown.add(defaultOptionGeneration, null);

            const uniqueGenerationNames = new Set();
            generations.data.forEach(gen => {
                if (!uniqueGenerationNames.has(gen.name)) {
                    const option = document.createElement("option");
                    option.value = gen.id;
                    option.text = gen.name;
                    generationDropdown.add(option);

                    uniqueGenerationNames.add(gen.name);
                }
            });
        });
});
const defaultOptionBody = document.createElement("option");
defaultOptionBody.value = "-1";
defaultOptionBody.text = "-Select generation-";
bodyDropdown.add(defaultOptionBody, null);

generationDropdown.addEventListener("change", () => {

    const modelName = modelDropdown.options[modelDropdown.selectedIndex].text.replaceAll(" ", "+");
    const trim = generationDropdown.options[generationDropdown.selectedIndex].text;
    const currentUrl =
        `https://car-api2.p.rapidapi.com/api/bodies?make_id=${makeDropdown.value}&sort=type&model=${modelName}&verbose=yes&direction=asc&year=${yearDropdown.value}`

    fetch(currentUrl, options)
        .then(response => response.json())
        .then(bodies => {

            const body = bodies.data;
            // Clear existing options
            bodyDropdown.innerHTML = "";
            bodyDropdown.add(defaultOptionGeneration, null);

            // Add new options
            body.forEach(body => {
                console.log(body.make_model_trim.name);
                if (trim === body.make_model_trim.name) {
                    const option = document.createElement("option");
                    option.value = body.id;
                    option.text = body.type;
                    bodyDropdown.add(option);
                }

            });
        });
});
