document.addEventListener('DOMContentLoaded', function() {
    var idBtn = document.getElementById('id-btn');
    var nicknameBtn = document.getElementById('nickname-btn');

    idBtn.addEventListener('click', function(event) {
        event.preventDefault();
        var providerId = document.getElementById('providerId').value;
        if(providerId.trim() === '') {
            alert('아이디를 입력해주세요.');
            return;
        }
        debugger;
        fetch('/api/signup/checkId', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ providerId: providerId}),
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
            })
            .then(data => {
                if (data) {
                    alert('이미 사용 중인 아이디입니다.');
                } else {
                    alert('사용 가능한 아이디입니다.');
                }
            })
            .catch(error => {
                alert('이미 사용 중인 아이디입니다');
                console.error('Error:', error);
            });
    });

    nicknameBtn.addEventListener('click', function(event) {
        event.preventDefault();
        var nickname = document.getElementById('nickname').value;
        if(nickname.trim() === '') {
            alert('닉네임을 입력해주세요.');
            return;
        }
        fetch('/api/signup/checkNickname', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ nickname: nickname }),
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
            })
            .then(data => {
                if (data) {
                    alert('이미 사용 중인 닉네임입니다.');
                } else {
                    alert('사용 가능한 닉네임입니다.');
                }
            })
            .catch(error => {
                alert('이미 사용 중인 닉네임입니다.');
                console.error('Error:', error);
            });
    })
});