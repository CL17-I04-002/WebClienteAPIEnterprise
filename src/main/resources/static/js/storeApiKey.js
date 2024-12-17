/*const params = new URLSearchParams(window.location.search);
    const apiKey = params.get('apiKey');

    if (apiKey) {
        // Guardar en sessionStorage
        sessionStorage.setItem('apiKey', apiKey);
    }*/


    document.addEventListener('DOMContentLoaded', () => {
        // Get parameter from '?apiKey=...'
        const apiKeyFound = sessionStorage.getItem('apiKey');
        if(!apiKeyFound){
            console.log("Se ejecuto el metodo storeApiKey");
            const params = new URLSearchParams(window.location.search);
                const apiKey = params.get('apiKey');

                console.log("Método store Api Key, valor de Api Key " + apiKey);
                // It saves the API Key from session storage
                if (apiKey) {
                    sessionStorage.setItem('apiKey', apiKey);
                    console.log('API Key almacenada en sessionStorage:', apiKey);
                }
        } else{
            console.log("API KEY encontrada");
        }
    });