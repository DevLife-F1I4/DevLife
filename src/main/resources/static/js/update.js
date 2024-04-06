document.addEventListener('DOMContentLoaded', function () {
    const updateForm = document.querySelector('.update-form');
    updateForm.addEventListener('submit', function (event) {
        event.preventDefault();

        const url = '/board/' + document.getElementById('id').value;
        const category = document.getElementById('category').value;
        const title = document.getElementById('title').value;
        const content = document.getElementById('content').value;

        fetch(url + '/update', {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                id: id,
                category: category,
                title: title,
                content: content
            })
        })
            .then(response => {
                if (response.ok) {
                    alert("게시물 수정 완료");
                    window.location.replace(url);
                } else {
                    alert("게시물 수정 실패");
                }
            })
            .catch(error => console.error(error.message));
    });
});
