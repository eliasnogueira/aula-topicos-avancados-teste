$(document).ready(function () {
    $('.collapsible-header').collapsible();
});

function a_onClick() {
    $('#duvidas').click();
    $('html, body').animate({scrollTop:$(document).height()}, 'slow');
}

var greenItem = 'light-green lighten-5'

$('#check_instalacao_java').click(function () {
    this.checked === true ? $('#instalacao_java').addClass(greenItem) : $('#instalacao_java').removeClass(greenItem);
});

$('#check_configuracao_java').click(function () {
    this.checked === true ? $('#configuracao_java').addClass(greenItem) : $('#configuracao_java').removeClass(greenItem);
});

$('#check_ide').click(function () {
    this.checked === true ? $('#ide').addClass(greenItem) : $('#ide').removeClass(greenItem);
});

$('#check_instalar_nodejs').click(function () {
    this.checked === true ? $('#instalar_nodejs').addClass(greenItem) : $('#instalar_nodejs').removeClass(greenItem);
});

$('#check_projeto').click(function () {
    this.checked === true ? $('#projeto').addClass(greenItem) : $('#projeto').removeClass(greenItem);
});

$('#check_instalar_http_server').click(function () {
    this.checked === true ? $('#instalar_http_server').addClass(greenItem) : $('#instalar_http_server').removeClass(greenItem);
});

$('#check_importar').click(function () {
    this.checked === true ? $('#importar').addClass(greenItem) : $('#importar').removeClass(greenItem);
});

$('#check_drivers').click(function () {
    this.checked === true ? $('#drivers').addClass(greenItem) : $('#drivers').removeClass(greenItem);
});