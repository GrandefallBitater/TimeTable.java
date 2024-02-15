$(document).ready(function () {
    var in3 = $('#refreshTimeTable');
    in3.hide();

    let activeFileCount = 0;

    let audience = $('#createTimeTable > .UploadContainer > form > label:nth-child(1)');
    let teachers = $('#createTimeTable > .UploadContainer > form > label:nth-child(2)');
    let plan = $('#createTimeTable > .UploadContainer > form > label:nth-child(3)');

    $('#submit > input').hide();
    audience.hide();
    teachers.hide();
    plan.hide();

    $('#createTimeTable > .UploadContainer > div.buttonGroup > button:nth-child(1)').click( function () {
        audience.show();
        teachers.hide();
        plan.hide();
    });


    $('#createTimeTable > .UploadContainer > div.buttonGroup > button:nth-child(2)').click( function () {
        audience.hide();
        teachers.show();
        plan.hide();
    });

    $('#createTimeTable > .UploadContainer > div.buttonGroup > button:nth-child(3)').click( function () {
        audience.hide();
        teachers.hide();
        plan.show();
    });


    $('.input-file input[type=file]').on('change', function(){
        let file = this.files[0];
        if(this.files[0] != null){
            activeFileCount += 1;
        } else {
            activeFileCount -= 1;
        }
        $(this).next().html(file.name);
        if(activeFileCount === 3){
            $('#submit > input').show();
        }
    });

    $("#flexSwitchCheckDefault").change(function () {

        if ($(this).prop('checked') == true) {
            var in2 = $('#createTimeTable');
            in2.hide();
            var in1 = $('#refreshTimeTable');
            in1.show();
        } else {
            var in2 = $('#createTimeTable');
            in2.show();
            var in1 = $('#refreshTimeTable');
            in1.hide();
        }
    });

    var create = $('#createTimeTableDiv');
    create.hide();
    var files = $('.UploadContainer');

    $("#submit").on('click', function () {
        files.hide();
        create.show();
    });
});