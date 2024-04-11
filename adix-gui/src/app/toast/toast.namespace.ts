export class ToastContent {
  header: string;
  body: string;
  delay?: number;

  constructor(
    header: string,
    body: string,
    delay?: number
  ) {
    this.header = header;
    this.body = body;
    this.delay = delay;
  }
}
