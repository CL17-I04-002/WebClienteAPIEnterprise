function setRedirectUrl(element){
    const redirectUrl = element.getAttribute('data-redirect-url');
    document.getElementById('redirectUrl').value = redirectUrl;
}