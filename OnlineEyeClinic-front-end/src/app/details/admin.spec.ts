import { Admin } from './admin';

describe('Admin', () => {
  it('should create an instance', () => {
    expect(new Admin(0,'','','','','')).toBeTruthy();
  });
});
