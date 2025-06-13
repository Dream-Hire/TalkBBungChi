db = db.getSiblingDB('talkbbungchi');

// 사용자 계정 생성
db.createUser({
  user: 'user',
  pwd: 'userpw',
  roles: [
    {
      role: 'readWrite',
      db: 'talkbbungchi'
    }
  ]
});

// chat_messages 컬렉션 생성 및 예시 스키마 적용
db.createCollection("chat_messages", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["sender_id", "chat_room_id", "content", "created_at", "type", "is_read"],
      properties: {
        sender_id:    { bsonType: "int",     description: "사용자 또는 페르소나 ID" },
        chat_room_id: { bsonType: "int",     description: "채팅방 ID" },
        content:      { bsonType: "string",  description: "메시지 본문" },
        created_at:   { bsonType: "date",    description: "생성 일자" },
        type:         { bsonType: "string",  enum: ["TEXT", "IMAGE"], description: "메시지 타입" },
        is_read:      { bsonType: "bool",    description: "읽음 여부" }
      }
    }
  }
});

// ai_learning_data 컬렉션 생성 및 예시 스키마 적용
db.createCollection("ai_learning_data", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["persona_id", "data_type", "content"],
      properties: {
        persona_id: { bsonType: "int",     description: "페르소나 ID" },
        data_type:  { bsonType: "string",  enum: ["IMAGE", "TXT", "CONVERSATION"], description: "데이터 유형" },
        content:    { bsonType: "string",  description: "원본 텍스트" },
        file_path:  { bsonType: ["string", "null"], description: "S3 저장 경로 (IMAGE인 경우만)" }
      }
    }
  }
});
