let dataTable;
let dataTableInitialized = false;

const initDataTable = async () => {
    if(dataTableInitialized){
        dataTable.destroy;
    }
    dataTable = $("#datatable_customer_task").DataTable({});

    dataTableInitialized = true;
    console.log("DataTable inicializado");
};

window.addEventListener("load", async () => {
    console.log("Inicializando DataTable");
    await initDataTable();
});