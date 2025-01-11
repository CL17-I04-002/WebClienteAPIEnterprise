function openModal(){
    const modal = document.getElementById("customerModal");

    const bootstrapModal = new bootstrap.Modal(modal);
    bootstrapModal.show();
    console.log("modal abierto");
}