db = db.getSiblingDB('talkbbungchi');

// ����� ���� ����
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

// chat_messages �÷��� ���� �� ���� ��Ű�� ����
db.createCollection("chat_messages", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["sender_id", "chat_room_id", "content", "created_at", "type", "is_read"],
      properties: {
        sender_id:    { bsonType: "int",     description: "����� �Ǵ� �丣�ҳ� ID" },
        chat_room_id: { bsonType: "int",     description: "ä�ù� ID" },
        content:      { bsonType: "string",  description: "�޽��� ����" },
        created_at:   { bsonType: "date",    description: "���� ����" },
        type:         { bsonType: "string",  enum: ["TEXT", "IMAGE"], description: "�޽��� Ÿ��" },
        is_read:      { bsonType: "bool",    description: "���� ����" }
      }
    }
  }
});

// ai_learning_data �÷��� ���� �� ���� ��Ű�� ����
db.createCollection("ai_learning_data", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["persona_id", "data_type", "content"],
      properties: {
        persona_id: { bsonType: "int",     description: "�丣�ҳ� ID" },
        data_type:  { bsonType: "string",  enum: ["IMAGE", "TXT", "CONVERSATION"], description: "������ ����" },
        content:    { bsonType: "string",  description: "���� �ؽ�Ʈ" },
        file_path:  { bsonType: ["string", "null"], description: "S3 ���� ��� (IMAGE�� ��츸)" }
      }
    }
  }
});
