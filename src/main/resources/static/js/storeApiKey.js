const params = new URLSearchParams(window.location.search);
    const apiKey = params.get('apiKey');

    if (apiKey) {
        // Guardar en sessionStorage
        sessionStorage.setItem('apiKey', apiKey);
    }