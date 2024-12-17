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

/*
document.addEventListener('DOMContentLoaded', () => {
    const apiKey = sessionStorage.getItem('apiKey');

    if (apiKey) {
        console.log('API Key recuperada:', apiKey);

        const linkElements = document.querySelectorAll('a');
        linkElements.forEach(link => {
            if (link.href.includes('/enterprise')) {
                link.addEventListener('click', async (e) => {
                    e.preventDefault();

                    try {
                        const response = await fetch(link.href, {
                            headers: {
                                'Authorization': `Bearer ${apiKey}`
                            }
                        });

                        if (response.ok) {
                            const html = await response.text();
                            document.body.innerHTML = html;
                        } else {
                            console.error('Error en la respuesta del servidor:', response.status);
                        }
                    } catch (error) {
                        console.error('Error al realizar la solicitud:', error);
                    }
                });
            }
        });
    } else {
        console.warn('No se encontró una API Key en sessionStorage.');
    }
});*/

    function getApiKey(url){
        const apiKey = sessionStorage.getItem("apiKey");
        console.log("URL a apuntar: " + url);

        if(apiKey != null){
            console.log("Se envió la petición");
            fetch(url, {
                headers: {
                    "Authorization": `Bearer ${apiKey}`
                },
                redirect: "follow"
            })
            .then(response => {
                if(response.ok){
                    console.log("Redirección completada automáticamente");
                    return response.text();
                } else{
                    throw new Error("Network response was not ok");
                }
            })
            .then(html => {
                        // Actualizamos la URL del navegador
                        window.history.pushState({}, '', '/enterprise');

                        // Una vez obtenemos el HTML renderizado del backend, inyectamos el contenido
                        document.open();
                        document.write(html);
                        document.close();
                    })
            .catch(error => {
                console.error('There has been a problem with your fetch operation:', error);
            });
        } else{
            console.error("API Key not found in Session Storage");
        }
      }