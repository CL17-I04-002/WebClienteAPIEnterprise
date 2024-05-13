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
}