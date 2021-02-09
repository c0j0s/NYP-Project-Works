console.log("wait first")
setTimeout(() => {
    var list = document.querySelectorAll(".scenic-list a")
    list.forEach(element => {
        console.log(element.href)
    });
}, 2000);
