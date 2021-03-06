$(document).ready(function() {
    var colseModalWindowTimeout;
    var table = $('#example').DataTable({
          "order": [[2, "desc" ]],
          responsive: true,
          "oLanguage": {
            "sLengthMenu": "_MENU_ wierszy"
          },
          "sDom":
                "<'row'<'col-sm-4'f><'col-sm-4'i><'col-sm-4'l>>" +
                "<'row'<'col-sm-12'tr>>" +
                "<'row'<'col-sm-8 col-xs-12'B><'col-sm-4 col-xs-12'p>>",
                buttons: [
                    {
                        extend: 'copyHtml5',
                        text: '<i class="fa fa-clipboard"></i> Kopiuj do Schowka',
                        exportOptions: {
                            columns: '0, 1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 10, 11, 12, 13'
                        }
                    },
                    {
                        extend: 'excelHtml5',
                        text: '<i class="fa fa-file-excel-o"></i> Eksport do Excel',
                        exportOptions: {
                            columns: '0, 1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 10, 11, 12, 13'
                        }
                    },
                    {
                        extend: 'csvHtml5',
                        charset: 'utf8',
                        bom: true,
                        text: '<i class="fa fa-file-text-o"></i> Eksport do CSV',
                        exportOptions: {
                            columns: '0, 1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 10, 11, 12, 13'
                        }
                    },
                    {
                        text: '<i class="fa fa-refresh"></i> Odśwież',
                        action: function ( e, dt, node, config ) {
                            table.ajax.reload();
                        }
                    }
                ],
          'ajax'     : {
            "url"    : "compleads/reccomendationstatus/1798543",
            "type"   : "get",
            "dataSrc": function (json) {
              var return_data = new Array();
              for(var i=0; i< json.length; ++i) {
                var fullUserName = json[i].user.firstName + " " + json[i].user.lastName;
                return_data.push({
                    'id': json[i].id,
                    'nip': json[i].nip,
                    'companyName': json[i].companyName,
                    'recommendationStatus': json[i].recommendationStatus.second,
                    'orderStatus': json[i].orderStatus.second,
                    'idTel' : json[i].idTel,
                    'contactPerson' : json[i].contactPerson,
                    'contactPersonPhone' : json[i].contactPersonPhone,
                    'expectedService': json[i].expectedService.second,
                    'expectedInstallationDate' : json[i].expectedInstallationDate !== null ? moment(json[i].expectedInstallationDate).format("YYYY-MM-DD") : '---',
                    'creationDate' : moment(json[i].creationDate).format("YYYY-MM-DD")
                });
              }
              return return_data;
            }
          },
          "columns" : [
            {'data': 'id'},
            {'data': 'creationDate'},
            {'data': 'companyName'},
            {'data': 'nip'},
            {'data': 'contactPerson'},
            {'data': 'contactPersonPhone'},
            {'data': 'recommendationStatus'},
            {'data': 'orderStatus'},
            {'data': 'idTel'},
            {'data': 'expectedService'},
            {'data': 'expectedInstallationDate'},
        ],
    });

     $('#example tbody').on('click', 'td:not(:first-child)', function (e) {
        var data = table.row( $(this).parent() ).data();
        var compId = data['id'];
        cleanMessage();

        $.get( "compleads/" + compId, function( data ) {
            var fullUserName = data.user.firstName + " " + data.user.lastName;
            $('.modal-container').load("components/opl/change-order.hbs",function(template) {
                var template = Handlebars.compile(template);
                $(this).html(template(data));
                $('#modal-change-order').modal({show:true});
            });
        }).fail(function() {
            toastr.error('Nie udało się pobrać szczegółów zgłoszenia');
        });

     });

    $('.modal-container').on('click', '#modal-change-order #send', function (e) {
        var telIdPattern = /^()$|^[a-zA-Z0-9]{15}$/gm;
        var orderStatus = $("#comp-lead-modal #order-status").val();
        var telId = $("#comp-lead-modal #id-tel").val();
        var isTelIdCorrect = telIdPattern.test(telId);
        if(!isTelIdCorrect) {
            $("#error-message").html("Nieprawidłowy Nr TEL. Proszę poprawić").addClass("alert alert-danger");
            return;
        }
        if(orderStatus === '1798545') {
            if(telId.length === 0)  {
                $("#error-message").html("Dla pola Stan OPL: Realizacja, wymagane jest podanie Nr TEL").addClass("alert alert-danger");
                return;
            }
        }
        sendFormByAjax('form', false, function() {
            $("#error-message").html("<div>Zdarzenie zostało poprawnie zapisane w bazie danych</div> <div>Za trzy sekundy samoczynnie okno się zamknie</div>").removeClass("alert-danger").addClass("alert alert-success");
            $("#comp-lead-modal").scrollTop($('#error-message').offset().top);
            colseModalWindowTimeout = setTimeout(function() {
               $('#comp-lead-modal').modal('toggle');
            }, 2900);
            setTimeout(function(){
               table.ajax.reload();
               toastr.success('Aktualizacja danych w tabeli przebiegła poprawnie');
            }, 3000);
        });
    });

    $(".close-event").click(function (e) {
         clearTimeout(colseModalWindowTimeout);
         $('#comp-lead-modal').modal('toggle');
    });

});