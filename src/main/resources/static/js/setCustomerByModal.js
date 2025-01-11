function selectEnterprise(button){
    const enterpriseId = button.getAttribute('data-id');
    const enterpriseName = button.getAttribute('data-name');

    document.getElementById('customerId').value = enterpriseId;
    document.getElementById('customerName').value = enterpriseName;
}