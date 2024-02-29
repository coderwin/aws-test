import React from 'react'

function Info({memberInfo}) {
  
  return (
    <div className='member-info'>
      {/* id */}
      <div>{memberInfo.id}</div>
      {/* nickname */}
      <div>{memberInfo.nickname}</div>
      {/* profile image url */}
      <div>
        <img 
          alt={"닉네임_" + memberInfo.nickname}
          src={memberInfo.profileUrl}  
        />
      </div>
    </div>
  );
}

export default Info