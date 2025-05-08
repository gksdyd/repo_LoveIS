function goList(page) {
    $("input[name=thisPage]").val(page);
    refreshMember();
}