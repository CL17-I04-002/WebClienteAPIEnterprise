document.addEventListener('DOMContentLoaded', () => {
    const apiKey = sessionStorage.getItem('apiKey');
    if (apiKey) {
        const hiddenApiKeyField = document.getElementById('hiddenApiKey');
        if (hiddenApiKeyField) {
            hiddenApiKeyField.value = apiKey;
            console.log('API Key configurada en el formulario: ' + apiKey);
        } else {
            console.warn('No se encontró el campo oculto para la API Key.');
        }
    } else {
        console.warn('No se encontró una API Key en sessionStorage.');
    }
});