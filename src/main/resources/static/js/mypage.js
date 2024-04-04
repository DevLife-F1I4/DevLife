const nicknameBtn= document.getElementById('update-nickname-btn');

if (nicknameBtn) {
    // 클릭 이벤트가 감지되면 수정 API 요청
    nicknameBtn.addEventListener('click', event => {
        let params = new URLSearchParams(location.search);

        fetch(`/api/user/me`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                nickname: document.getElementById('nickname').value
            })
        }).then(() => {
            alert('수정이 완료되었습니다');
            location.replace(`/mypage`);
        });
    });
}