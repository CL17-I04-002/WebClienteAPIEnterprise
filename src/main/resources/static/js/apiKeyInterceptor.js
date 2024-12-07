/*
// Función para obtener la API Key del sessionStorage
function getApiKey() {
    return sessionStorage.getItem('apiKey');
}

// Añadir la API Key a todas las solicitudes fetch
(function interceptFetch() {
    const originalFetch = window.fetch;
    window.fetch = function (input, init = {}) {
        const apiKey = getApiKey();
        if (apiKey) {
            // Añadir el header Authorization
            init.headers = {
                ...init.headers,
                'Authorization': `Bearer ${apiKey}`,
            };
        }
        return originalFetch(input, init);
    };
})();*/

document.addEventListener('DOMContentLoaded', () => {
    // Recuperar la API Key almacenada en sessionStorage
    const apiKey = sessionStorage.getItem('apiKey');

    // Verificar que exista una API Key
    if (apiKey) {
        console.log('API Key recuperada:', apiKey);

        // Interceptar todas las solicitudes fetch para añadir la API Key
        const originalFetch = window.fetch;
        window.fetch = async (input, init = {}) => {
            // Verificar si init es un objeto y agregar los headers
            init.headers = {
                ...init.headers,
                'Authorization': `Bearer ${apiKey}` // O cualquier formato que uses
            };
            return originalFetch(input, init);
        };
    } else {
        console.warn('No se encontró una API Key en sessionStorage.');
    }
});