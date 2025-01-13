function cleanApiKey(){
    document.addEventListener('DOMContentLoaded', () => {
        sessionStorage.removeItem("apiKey");
    });
}