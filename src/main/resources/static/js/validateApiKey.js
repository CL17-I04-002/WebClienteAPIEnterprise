/*
document.addEventListener("DOMContentLoaded", function() {
    const transactorLink = document.getElementById("submenu-transactor");

    transactorLink.addEventListener("click", function() {
        const apiKey = getCookieValue("apiKey");

        // alert(apiKey);

        if (apiKey === null || apiKey === '') {
                    // La cookie apiKey es nula, cargar el primer JS
                    // alert("El api key es null");
                    loadScript("js/ModalApiTransactor.js");
                } else {
                    // La cookie apiKey no es nula, cargar el segundo JS
                    // alert("El api key no es null");
                    loadScript("js/Submenu.js");
                }
    });
});

function getCookieValue(cookieName) {
    const name = cookieName + "=";
    const decodedCookie = decodeURIComponent(document.cookie);
    const cookieArray = decodedCookie.split(';');
    for (let i = 0; i < cookieArray.length; i++) {
        let cookie = cookieArray[i];
        while (cookie.charAt(0) === ' ') {
            cookie = cookie.substring(1);
        }
        if (cookie.indexOf(name) === 0) {
            return cookie.substring(name.length, cookie.length);
        }
    }
    return "";
}
// Función para cargar un script dinámicamente
function loadScript(scriptUrl) {
    const script = document.createElement("script");
    script.src = scriptUrl;
    document.head.appendChild(script);
}*/



document.addEventListener("DOMContentLoaded", function () {

    // Seleccionar todos los enlaces dentro del menú
    const menuLinks = document.querySelectorAll(".sub-menu a");

    // Agregar evento click a cada enlace
    menuLinks.forEach(link => {
        link.addEventListener("click", function (event) {
            const apiKey = sessionStorage.getItem("apiKey"); // Recuperar la API Key desde sessionStorage

            if (!apiKey) {
                console.log("API KEY no encontrada")
                // Prevenir la navegación
                event.preventDefault();

                // Mostrar el modal para pedir la API Key
                const modal = new bootstrap.Modal(document.getElementById("apiKeyModal"));
                modal.show();

                // Enlazar el botón de enviar del modal para guardar la API Key
                /*const apiKeyForm = document.getElementById("apiKeyForm");
                apiKeyForm.addEventListener("submit", function (e) {
                    console.log("Paso y debería de mostrar form")
                    e.preventDefault();
                    const apiKeyInput = document.getElementById("apiKeyId").value;

                    if (apiKeyInput) {
                        // Guardar la API Key en sessionStorage
                        sessionStorage.setItem("apiKey", apiKeyInput);

                        // Cerrar el modal y redirigir al enlace original
                        modal.hide();
                        window.location.href = link.href;
                    } else {
                        alert("Por favor, ingresa una API Key válida.");
                    }
                }, { once: true });*/
            } else{
                console.log("API KEY encontrada")
            }
        });
    });
});