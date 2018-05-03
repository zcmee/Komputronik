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
            {'data': 'zipCode'},
            {'data': 'installationAddress'},
            {'data': 'contactPersonPhone'},
            {'data': 'recommendationStatus'},
            {'data': 'orderStatus'},
            {'data': 'idTel'},
            {'data': 'expectedService'},
            {'data': 'expectedInstallationDate'}
        ],
    } );

     $('#example tbody').on('click', 'td:not(:first-child)', function (e) {
        var data = table.row( $(this).parent() ).data();
        var compId = data['id'];
        $("#error-message").removeClass("alert-success")
                           .removeClass("alert-danger")
                           .html("");

        $.get( "compleads/" + compId, function( data ) {
            var fullUserName = data.user.firstName + " " + data.user.lastName;

            $("#comp-lead-modal #id-complaint").html(data.id);
            $("#comp-lead-modal #creation-user").val(fullUserName);
            $("#comp-lead-modal #id-tel").val(data.idTel);
            $("#comp-lead-modal #nip").val(data.nip);
            $("#comp-lead-modal #company-name").val(data.companyName);
            $("#comp-lead-modal #id").val(data.id);
            $("#comp-lead-modal #contact-person").val(data.contactPerson);
            $("#comp-lead-modal #contact-person-phone").val(data.contactPersonPhone);
            $("#comp-lead-modal #excepted-service").val(data.expectedService.first).change();
            $("#comp-lead-modal #order-status").val(data.orderStatus.first).change();
            $("#comp-lead-modal #recommendation-status").val(data.recommendationStatus.first).change();
            $("#comp-lead-modal #creation-date").val(moment(data.creationDate).format("YYYY-MM-DD"));
            if(data.expectedInstallationDate !== null) {
                $("#comp-lead-modal #expected-installation-date").val(moment(data.expectedInstallationDate).format("YYYY-MM-DD"));
            } else {
                $("#comp-lead-modal #expected-installation-date").val('');
            }
        });

        $('#comp-lead-modal').modal({show:true});
     });

    $("#send").click(function (e) {
        var expectedInstallationDate = $("#expected-installation-date").val();
        if(expectedInstallationDate.length === 0)  {
            $("#error-message").html("Preferowana data instalacji jest polem wymagana").addClass("alert alert-danger");
            return;
        }

        sendFormByAjax('form', false, function() {
            $("#error-message").html("<div>Zdarzenie zostało poprawnie zapisane w bazie danych</div> <div>Za trzy sekundy samoczynnie okno się zamknie</div>").removeClass("alert-danger").addClass("alert alert-success");
            $("#comp-lead-modal").scrollTop($('#error-message').offset().top);
            colseModalWindowTimeout = setTimeout(function(){
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