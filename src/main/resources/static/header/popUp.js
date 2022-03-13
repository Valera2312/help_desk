
$(document).ready(function(){
    PopUpHide();
    popupAddPriorityHide();
    popupAddExecutorHide();
    popupAddStatusHide();
    popupAddCommentHide();

    let priorityTd = document.getElementById("priority");
    if (priorityTd.innerText === "Высокий") {

        priorityTd.style.backgroundColor = "red";

    }else if (priorityTd.innerText === "Средний") {
        priorityTd.style.backgroundColor = "orange";
    }else if (priorityTd.innerText === "Низкий"){
        priorityTd.style.backgroundColor = "yellow";
    }
});
function PopUpShow(){
    $("#popup1").show();
}
function PopUpHide(){
    $("#popup1").hide();
}


function popupAddPriorityShow(){
    $("#popupAddPriority").show();
}
function popupAddPriorityHide(){
    $("#popupAddPriority").hide();
}

function popupAddExecutorShow(){
    $("#popupAddExecutor").show();
}
function popupAddExecutorHide(){
    $("#popupAddExecutor").hide();
}

function popupAddStatusShow(){
    $("#popupAddStatus").show();
}
function popupAddStatusHide(){
    $("#popupAddStatus").hide();
}
function popupAddCommentShow(){
    $("#popupAddComment").show();
}
function popupAddCommentHide(){
    $("#popupAddComment").hide();
}
