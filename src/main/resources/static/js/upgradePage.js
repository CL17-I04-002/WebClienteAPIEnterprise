document.addEventListener("keydown", function (event) {
    if (event.key === "F5" || (event.ctrlKey && event.key === "r")) {
        console.log("El usuario presionó F5 o Ctrl+R.");
        getApiKey();
        ///event.preventDefault();
    }
});