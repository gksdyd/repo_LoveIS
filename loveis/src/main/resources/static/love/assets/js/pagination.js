function goList(page) {
    fetch("/love/member/MemberLoveProc", {    // payList fragment만 반환하는 컨트롤러
        method: 'POST',  // POST 요청
        body: new URLSearchParams({  // POST 데이터 설정
            "thisPage" : page
        })
    })
    .then(res => res.text())
    .then(html => {
        $("#removeMember").remove();
        $("#refreshMember").prepend(html);
    });
}