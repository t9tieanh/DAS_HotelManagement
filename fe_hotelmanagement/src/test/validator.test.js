import { validateEmail, validatePhoneNumber } from '.././utils/Validate';

describe('validateEmail', () => {
  it('returns true for valid emails', () => {
    expect(validateEmail('test@example.com')).toBe(true);
    expect(validateEmail('user.name@domain.co')).toBe(true);
  });

  it('returns false for invalid emails', () => {
    expect(validateEmail('invalid')).toBe(false);
    expect(validateEmail('user@.com')).toBe(false);
  });
});

describe('validatePhoneNumber', () => {
  it('returns true for valid phone numbers', () => {
    expect(validatePhoneNumber('123456')).toBe(true);
    expect(validatePhoneNumber('0987654321')).toBe(true);
  });

  it('returns false for invalid phone numbers', () => {
    expect(validatePhoneNumber('12345')).toBe(false);
    expect(validatePhoneNumber('abc123456')).toBe(false);
  });
});
