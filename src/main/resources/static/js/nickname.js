function submitForm(event) {
    event.preventDefault(); // 기본 동작(폼 제출) 방지

    const newNickname = document.getElementById('newNickname').value;

    // JSON 데이터 생성
    const jsonData = {
        nickname: newNickname
    };

    // fetch를 사용하여 PUT 요청 보내기
    fetch('/api/user/me', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonData)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('네트워크 오류');
            }
            alert("닉네임 변경 성공");
            location.replace('/mypage');
        })
        .catch(error => {
            console.error('오류:', error);
        });
}