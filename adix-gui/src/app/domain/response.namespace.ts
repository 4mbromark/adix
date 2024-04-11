export class Response {
  id: number;
  error: boolean;
  message: string;
}

export class FetchRecordResponse<T> extends Response {
  records: T[];
}
