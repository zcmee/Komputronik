function sendFormByAjax(idForm, isAttachment, fun) {
    var contentForm;
    var $form = $('#' + idForm);
    if(isAttachment) contentForm = new FormData(($('#'+idForm))[0]);
    else contentForm = $('#'+idForm).serialize();
    var $button = $(this);

    if($button.attr('requestRunning') === 'true') { return; }
    $button.attr('requestRunning', true);

    $.ajax({
        type: $form.attr('method'),
        url: $form.attr('action'),
        cache: false,
        encoding: "UTF-8",
        data: contentForm,
        contentType: isAttachment ? false : 'application/x-www-form-urlencoded; charset=UTF-8',
        processData: false,
        success: function (datax) {
            fun(datax);
        },
        error: function(xhr) {
            var $element = $("#show-message");
            if ($element.length && xhr.status < 500) {
                addNegativeMessage(xhr.responseText);
            }
        },
        complete : function() {
            $button.attr('requestRunning', false);
        },
        statusCode: {
            500: function() {
                addNegativeMessage('Funkcjonalość chwilowo niedostępna. Proszę spróbować później.');
            }
        }
    });
}