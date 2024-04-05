const deleteBtn = document.getElementById('delete-btn');

deleteBtn.addEventListener('click', () => {
    const url = window.location.pathname;

    alert('삭제시 복구할 수 없습니다. 정말 삭제하시겠습니까?');

    // DELETE 요청 전송
    fetch(url, {
        method: 'DELETE',
    })
        .then((response) => {
            if (response.status === 200) {
                location.href = '/board/list';
            } else {
                console.log(response);
                alert('삭제 실패: ' + response.statusText);
            }
        })
        .catch((error) => {
            console.error(error);
            alert('오류 발생. 다시 시도해주세요.');
        });
});
