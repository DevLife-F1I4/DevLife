document.addEventListener('DOMContentLoaded', function () {
    const writeForm = document.querySelector('.write-form');
    writeForm.addEventListener('submit', function (event) {
        event.preventDefault();

        const category = document.getElementById('category').value;
        const grade = document.getElementById('grade').value;
        const title = document.getElementById('title').value;
        const content = document.getElementById('content').value;

            fetch('/board/write', {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    category: category,
                    grade: grade,
                    title: title,
                    content: content
                })
            })
                .then(response => {
                    if (response.ok) {
                        alert("게시물 작성 완료");
                        window.location.replace('/board/list');
                    } else {
                        alert("게시물 작성 실패");
                    }
                })
                .catch(error => console.error(error.message));
    });
});
