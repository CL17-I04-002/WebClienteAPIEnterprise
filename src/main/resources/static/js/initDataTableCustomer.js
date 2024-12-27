let dataTable;
let dataTableInitialized = false;

const initDataTable = async () => {
    if(dataTableInitialized){
        dataTable.destroy();
    }
    dataTable = $("#datatable_customer").DataTable({});

    dataTableInitialized = true;
    console.log("DataTable inicializado");
};

window.addEventListener("load", async() => {
    console.log("Inicializado DataTable");
    await initDataTable();
});