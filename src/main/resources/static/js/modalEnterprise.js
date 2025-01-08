function openModal(){
    const modal = document.getElementById("enterpriseModal");

    const bootstrapModal = new bootstrap.Modal(modal);
    bootstrapModal.show();
    console.log("modal abierto");
}