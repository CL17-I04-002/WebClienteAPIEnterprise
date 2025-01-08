function selectEnterprise(button){
    const enterpriseId = button.getAttribute('data-id');
    const enterpriseName = button.getAttribute('data-name');

    document.getElementById('enterpriseId').value = enterpriseId;
    document.getElementById('enterpriseName').value = enterpriseName;
}