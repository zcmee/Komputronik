$(document).ready(function() {
    var colseModalWindowTimeout;
    var $table = $('#example').DataTable({
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
                            columns: '0, 1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 10, 11'
                        }
                    },
                    {
                        extend: 'excelHtml5',
                        text: '<i class="fa fa-file-excel-o"></i> Eksport do Excel',
                        exportOptions: {
                            columns: '0, 1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 10, 11'
                        }
                    },
                    {
                        extend: 'csvHtml5',
                        charset: 'utf8',
                        bom: true,
                        text: '<i class="fa fa-file-text-o"></i> Eksport do CSV',
                        exportOptions: {
                            columns: '0, 1, 2, 3, 4, 5, 6, 6, 7, 8, 9, 10, 11'
                        }
                    },
                    {
                        text: '<i class="fa fa-refresh"></i> Odśwież',
                        action: function ( e, dt, node, config ) {
                            $table.ajax.reload();
                        }
                    }
                ],
            'ajax'     : {
            "url"    : "compleads/reccomendationstatus/active",
            "type"   : "get",
            "dataSrc": function (json) {
              var return_data = new Array();
              for(var i=0; i< json.length; ++i) {
                var fullUserName = json[i].user.firstName + " " + json[i].user.lastName;
                return_data.push({
                    'id': json[i].id,
                    'creationUser': fullUserName,
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
            {'data': 'creationUser'},
            {'data': 'creationDate'},
            {'data': 'companyName'},
            {'data': 'nip'},
            {'data': 'contactPerson'},
            {'data': 'contactPersonPhone'},
            {'data': 'recommendationStatus'},
            {'data': 'orderStatus'},
            {'data': 'idTel'},
            {'data': 'expectedService'},
            {'data': 'expectedInstallationDate'}
        ],
    });

     $('#example tbody').on('click', 'td:not(:first-child)', function (e) {
        var data = $table.row( $(this).parent() ).data();
        var compId = data['id'];
        cleanMessage();

        $.get( "compleads/" + compId, function( data ) {
            $('.modal-container').load("components/or/change-order.hbs",function(template) {
                var template = Handlebars.compile(template);
                $(this).html(template(data));

                $('.datepicker').datepicker({
                    autoclose: true,
                    format: "yyyy-mm-dd",
                    todayBtn: false,
                    startDate: '+1d',
                    orientation: "auto",
                    todayHighlight: true
                });
                $('#modal-change-order').modal({show:true});
            });
        }).fail(function() {
            toastr.error('Nie udało się pobrać szczegółów zgłoszenia');
        });

     });

    $('.modal-container').on('click', '#modal-change-order #send', function (e) {
        e.preventDefault();
        var expectedInstallationDate = $("#expected-installation-date").val();
        if(expectedInstallationDate.length === 0)  {
            addNegativeMessage("Preferowana data instalacji jest polem wymagana");
            return;
        }

        sendFormByAjax('form', false, function() {
            addPositiveMessage("<div>Zdarzenie zostało poprawnie zapisane w bazie danych</div> <div>Za trzy sekundy samoczynnie okno się zamknie</div>");
            $("#modal-change-order").scrollTop($('#show-message').offset().top);
            colseModalWindowTimeout = setTimeout(function(){
               $('#modal-change-order').modal('toggle');
            }, 2900);
            setTimeout(function() {
               $table.ajax.reload();
               toastr.success('Aktualizacja danych w tabeli przebiegła poprawnie');
            }, 3000);
        });
    });

    $('.modal-container').on('click', '.close-event', function (e) {
        clearTimeout(colseModalWindowTimeout);
        $('#modal-change-order, #modal-add-order').modal('toggle');
    });

    $("#add-lead").click(function(){
        $('.modal-container').load("components/or/add-order.html", function(){
            $('#modal-add-order').modal({show:true});
        });
    });

    $('.modal-container').on('click', '#modal-add-order #send', function (e) {
        if(new Validator().valid()) {

            sendFormByAjax.call(this, 'form', false, function() {
                document.getElementById('form').reset();
                addPositiveMessage("Dane zostały prawidłowo zapisane w bazie danych.</br>Możesz dodać kolejne zgłoszenie lub zamknąć okno.");
                $table.ajax.reload();
            });
        } else {
            addNegativeMessage("Uzupełnij poprawnie formularz");
        }
    });

 });

