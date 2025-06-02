const ENGLISH = /[a-zA-Z]/;
const KOREAN = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;

function elasticSearch() {
    $("#elasticSearchList").empty();
    const inputVal = $("#elasticSearch").val();

    if (inputVal === "") {
        return;
    }

    let text;
    if (ENGLISH.test(inputVal)) {
        let temp = inputVal.toUpperCase();
        text = `SELECT engName, url FROM loveis WHERE engName LIKE '%${temp}%'`;
    } else if (KOREAN.test(inputVal)) {
        text = `SELECT name, url FROM loveis WHERE name LIKE '%${inputVal}%'`;
    } else {
        return;
    }

    const requestBody = {
        query: text
    };

    // fetch("http://localhost:9200/_sql?format=json&pretty", {
    fetch("http://3.38.103.31:9200/_sql?format=json&pretty", {
    method: "POST",
    headers: {
        "Content-Type": "application/json"
    },
    body: JSON.stringify(requestBody)
    })
    .then(res => res.json())
    .then(data => {
        for (let i = 0; i < data.rows.length; i++) {
            let list = "<li class='searchList'><a href=" + data.rows[i][1] + ">" + data.rows[i][0] + "</a></li>";
            $("#elasticSearchList").append(list);
        }
    });
}