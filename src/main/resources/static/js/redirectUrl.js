function setRedirectUrl(element){
            console.log("Se ejecuta el redirect URL");
            const redirectUrl = element.getAttribute('data-redirect-url');
            document.getElementById('redirectUrl').value = redirectUrl;
}