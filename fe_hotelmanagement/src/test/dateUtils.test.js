import { formatDate, calculateNightsAndDays } from '../utils/DateUtils';

describe('formatDate', () => {
  it('should return formatted date with weekday in Vietnamese', () => {
    const result = formatDate('2024-05-01');
    // Kết quả có thể là 'Th 4, 1 tháng 5'
    expect(result).toMatch(/Th \d, 1 tháng 5/);
  });

  it('should return empty string for invalid or empty date', () => {
    expect(formatDate(null)).toBe('');
    expect(formatDate(undefined)).toBe('');
    expect(formatDate('')).toBe('');
  });
});

describe('calculateNightsAndDays', () => {
  it('should correctly calculate days and nights for valid dates', () => {
    const result = calculateNightsAndDays('2024-05-01', '2024-05-05');
    expect(result).toEqual({ days: 4, nights: 3 });
  });

  it('should return 0 days and nights if check-out is same as or before check-in', () => {
    expect(calculateNightsAndDays('2024-05-01', '2024-05-01')).toEqual({ days: 0, nights: 0 });
    expect(calculateNightsAndDays('2024-05-02', '2024-05-01')).toEqual({ days: 0, nights: 0 });
  });

  it('should correctly handle 1-night stay', () => {
    const result = calculateNightsAndDays('2024-05-01', '2024-05-02');
    expect(result).toEqual({ days: 1, nights: 0 });
  });
});
