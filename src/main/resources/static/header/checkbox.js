function checkbox(id) {

    const inputs = document.getElementsByClassName("input_req");
    let chbox = document.getElementById(id);
    Array.prototype.forEach.call(inputs, function (el) {
        el.checked = false;
    });
    chbox.checked = true;
    if (chbox.checked) {
        if(document.getElementById('delete') != null){
            document.getElementById('delete').href = "/delete/" + chbox.id;
        }
        document.getElementById('add_status').action += chbox.id;
        document.getElementById('changePriority').action += chbox.id;
        document.getElementById('addExecutor').action += chbox.id;
        document.getElementById('addComment').action += chbox.id;
        document.getElementById('about_request').href += chbox.id;

    }
}