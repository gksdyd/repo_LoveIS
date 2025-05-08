var personalityArray = [];
var hobbyArray = [];

function selectMultiPersonal(e) {
    let children = $(e).next().find("li");

    if (personalityArray.includes($(e).val())) {
        for (let i = 0; i < personalityArray.length; i++) {
            if (personalityArray[i] == $(e).val()) {
                personalityArray.splice(i, 1);
            }
        }

        children.each(function () {
            if ($(this).attr("data-value") == $(e).val()) {
                $(this).css("color", "");
            }
        });
    } else {
        personalityArray.push($(e).val());
        children.each(function () {
            if ($(this).attr("data-value") == $(e).val()) {
                $(this).css("color", "red");
            }
        });
    }
}

function selectMultiHobby(e) {
    let children = $(e).next().find("li");

    if (hobbyArray.includes($(e).val())) {
        for (let i = 0; i < hobbyArray.length; i++) {
            if (hobbyArray[i] == $(e).val()) {
                hobbyArray.splice(i, 1);
            }
        }

        children.each(function () {
            if ($(this).attr("data-value") == $(e).val()) {
                $(this).css("color", "");
            }
        });
    } else {
        hobbyArray.push($(e).val());
        children.each(function () {
            if ($(this).attr("data-value") == $(e).val()) {
                $(this).css("color", "red");
            }
        });
    }
}