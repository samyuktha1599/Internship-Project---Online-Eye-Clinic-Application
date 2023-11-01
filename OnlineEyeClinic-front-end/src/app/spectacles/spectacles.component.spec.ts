import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SpectaclesComponent } from './spectacles.component';

describe('SpectaclesComponent', () => {
  let component: SpectaclesComponent;
  let fixture: ComponentFixture<SpectaclesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SpectaclesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SpectaclesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
