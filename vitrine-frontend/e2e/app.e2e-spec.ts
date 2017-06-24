import { VitrineWebClientPage } from './app.po';

describe('vitrine-web-client App', () => {
  let page: VitrineWebClientPage;

  beforeEach(() => {
    page = new VitrineWebClientPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
